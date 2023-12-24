import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

/**
 * Program modelling a computer's file system through terminal, without the use of File/Directory classes
 * @author abendrothj
 */
public class FileSys {
    private static final Folder rootDirObj = new Folder("/");
    private static Folder currentDir = rootDirObj;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean active = true;
        while(active) {
            System.out.print("prompt> ");
            String prompt = input.nextLine();
            if(prompt.startsWith("create ") && prompt.length() >= 8) {
                create(prompt.substring(7));
            }
            if(prompt.startsWith("cat ") && prompt.length() >= 5) {
                cat(prompt.substring(4));
            }
            if(prompt.startsWith("rm ") && prompt.length() >= 4) {
                rm(prompt.substring(3));
            }
            if(prompt.startsWith("mkdir ") && prompt.length() >= 7) {
                mkdir(prompt.substring(6));
            }
            if(prompt.startsWith("rmdir ") && prompt.length() >= 7) {
                rmdir(prompt.substring(6));
            }
            if(prompt.startsWith("cd ") && prompt.length() >= 4) {
                cd(prompt.substring(3));
            }
            if(prompt.startsWith("find ") && prompt.length() >= 6) {
                boolean matchFound = find(currentDir, prompt.substring(5));
                if(!matchFound) {
                    System.err.println("ERROR: no file exists with name specified");
                }
            }
            if(prompt.equals("ls")) {
                ArrayList<String> arr = currentDir.listContents();
                for(String e : arr) {
                    System.out.println(e);
                }
            }
            if(prompt.equals("du")) {
                long size = du(currentDir);
                System.out.println(size);
            }
            if(prompt.equals("pwd")) {
                if(currentDir.getPath().length() > 1) {
                    System.out.println(currentDir.getPath().substring(0, currentDir.getPath().length()-1));
                } else {
                    System.out.println(currentDir.getPath());
                }
            }
            if(prompt.startsWith("exit")) {
                active = false;
            }
        } input.close();
    }

    /**
     * Method to create a file in current directory, once executed, user fills in contents until "~"
     * @param fileName name to give file created
     */
    private static void create(String fileName) {
        if(!currentDir.hasFile(fileName) && !currentDir.hasDir(fileName)) {
            FileObj file = new FileObj(fileName, currentDir);
            Scanner in = new Scanner(System.in);
            while(true) {
                String line = "";
                if (in.hasNextLine()) {
                    line = in.nextLine();
                }
                if (line.isEmpty()) {
                    file.add("\n");
                }
                if (!line.isEmpty() && line.charAt(0) == '~') {
                    break;
                }
                if (line.contains("~")) {
                    file.add(line.substring(0, line.indexOf("~")));
                    break;
                }
                file.add(line);
            }
        } else {
            System.err.println("ERROR: file already exists");
        }
    }

    /**
     * Method to change working directories.
     * @param dirName directory to change to
     */
    private static void cd(String dirName) {
        if(dirName.equals("/")) { currentDir = rootDirObj; }
        else if(dirName.startsWith("/") && dirName.length() > 1) {
            currentDir = rootDirObj;
            String[] spl = dirName.split("/");
            for(String e : spl) {
               if(!e.isEmpty()) { cd(e); }
            }
        }
        else if(dirName.contains("/") && !dirName.startsWith("/")) {
            String[] dirSplit = dirName.split("/");
            for(String e : dirSplit) {
                if(!e.isEmpty()) { cd(e); }
            }
        }
        else if(dirName.equals("..")) {
            currentDir = currentDir.getParentDir();
        }
        else if(currentDir.hasDir(dirName)) {
            currentDir = currentDir.getDir(dirName);
        } else {
            System.err.println("ERROR: no directory exists with the name specified");
        }
    }

    /**
     * Prints the contents of file specified.
     * @param filename file to print
     */
    private static void cat(String filename) {
        if(currentDir.hasFile(filename)) {
            FileObj file = currentDir.getFile(filename);
            file.read();
        } else {
            System.err.println("ERROR: no file exists with the name specified");
        }
    }

    /**
     * Method to remove/delete a file.
     * @param filename file to remove
     */
    private static void rm(String filename) {
        if(currentDir.hasFile(filename)) {
            currentDir.remove(currentDir.getFile(filename));
        } else {
            System.err.println("ERROR: no file exists with the name specified");
        }
    }

    /**
     * Method to create a directory.
     * @param name of the directory to create
     */
    private static void mkdir(String name) {
        if(!currentDir.hasDir(name)&&!currentDir.hasFile(name)) {
            currentDir.add(new Folder(name, currentDir));
        } else {
            System.err.println("ERROR: there is already a file/directory named this");
        }
    }

    /**
     * Method to remove a directory.
     * @param name of the directory to remove
     */
    private static void rmdir(String name) {
        if(currentDir.hasDir(name)) {
            currentDir.remove(currentDir.getDir(name));
        } else {
            System.err.println("ERROR: no directory found");
        }
    }

    /**
     * Calculates disk usage of the current directory and all its subdirectories and their contents.
     * @param folder folder to calculate size
     * @return long number representing size in bytes.
     */
    private static long du(Folder folder) {
        long size = 0;
        for(Object e : folder.getContents()) {
            if(e instanceof FileObj) {
                size += ((FileObj) e).getSize();
            } else if(e instanceof Folder) {
                size += du((Folder) e);
            }
        } return size;
    }

    /**
     * Method to find filename throughout current directory and all subdirectories & print their location(s).
     * @param folder folder to search
     * @param name file to search for
     * @return boolean, if the file has been found at least once.
     */
    private static boolean find(Folder folder, String name) {
        boolean matchFound = false;
        for(Object obj : folder.getContents()) {
            if(obj instanceof Folder) {
                matchFound |= find(((Folder) obj), name);
            } else if(obj instanceof FileObj) {
                if(((FileObj) obj).getName().equals(name)) {
                    System.out.println(((FileObj) obj).getLocation());
                    matchFound = true;
                }
            }
        } return matchFound;
    }
}


/**
 * Class modelling a directory in a file system.
 */
class Folder {
    private String dirName = null;
    private Folder parentDir = null;
    private final ArrayList<Object> contents = new ArrayList<>();
    private String path = "";
    public String getPath() {
        return this.path;
    }

    /**
     * Construct a new Folder
     * @param dirName directory name
     * @param cd folder to create it in (current directory)
     */
    public Folder(String dirName, Folder cd) {
        this.path = cd.getPath()+dirName+"/";
        this.parentDir = cd;
        this.dirName = dirName;
    }

    /**
     * Constructor for the root directory
     * @param dirName name of directory
     */
    public Folder(String dirName) {
        this.path = "/";
        this.dirName = dirName;
        this.parentDir = this;
    }

    /**
     * Adds a new subdirectory inside this folder
     * @param f folder to add
     */
    public void add(Folder f) {
        this.contents.add(f);
    }

    /**
     * Adds a new file inside this folder
     * @param f file to add
     */
    public void add(FileObj f) {
        this.contents.add(f);
    }

    /**
     * Removes a folder from inside this directory
     * @param f folder to remove
     */
    public void remove(Folder f) { this.contents.remove(f); }

    /**
     * FileObj to remove from inside this directory
     * @param f FileObj to remove
     */
    public void remove(FileObj f) { this.contents.remove(f); }

    /**
     * Returns directory name
     * @return String of directory name
     */
    public String getDirName() {
        return this.dirName;
    }
    public Folder getParentDir() { return this.parentDir; }
    public ArrayList<Object> getContents() { return contents; }

    /**
     * Checks if folder contains directory specified
     * @param dir name of folder to search for
     * @return boolean has folder
     */
    public boolean hasDir(String dir) {
        if(!contents.isEmpty()) {
            for (Object i : contents) {
                if (i instanceof Folder && Objects.equals(((Folder) i).getDirName(), dir)) {
                    return true;
                }
            }
        } return false;
    }

    /**
     * Checks if folder contains file specified
     * @param filename name of file to check for
     * @return boolean has file
     */
    public boolean hasFile(String filename) {
        if(!contents.isEmpty()) {
            for (Object i : contents) {
                if (i instanceof FileObj && Objects.equals(((FileObj) i).getName(), filename)) {
                    return true;
                }
            }
        } return false;
    }

    /**
     * Returns Folder obj with name provided from current directory.
     * @param dir name of Folder
     * @return Folder obj
     */
    public Folder getDir(String dir) {
        for(Object i : contents) {
            if (i instanceof Folder && Objects.equals(((Folder) i).getDirName(), dir)) {
                return (Folder) i;
            }
        } return null;
    }

    /**
     * Returns FileObj in contents with name specified
     * @param filename name to search for
     * @return FileObj specified
     */
    public FileObj getFile(String filename) {
        for(Object i : contents) {
            if(i instanceof FileObj && Objects.equals(((FileObj) i).getName(), filename)) {
                return (FileObj) i;
            }
        } return null;
    }

    /**
     * Returns the contents of the directory sorted alphabetically.
     * @return ArrayList of Strings
     */
    public ArrayList<String> listContents() {
        ArrayList<String> elements = new ArrayList<>();
        for(Object i : contents) {
            if(i instanceof Folder) {
                elements.add(i + " (*)");
            } else {
                elements.add(i.toString());
            }
        } elements.sort(String.CASE_INSENSITIVE_ORDER);
        return elements;
    }
    @Override
    public String toString() {
        return dirName;
    }
}


/**
 * Class modelling a file in a file system
 */
class FileObj {
    private final ArrayList<String> contents = new ArrayList<>();
    private final String name;
    private final Folder location;
    public String getLocation() {
        return this.location.getPath()+name;
    }
    public String getName() {
        return this.name;
    }

    /**
     * Constructor
     * @param name name of file to create
     * @param location folder that contains this file (current directory)
     */
    public FileObj(String name, Folder location) {
        this.name = name;
        this.location = location;
        location.add(this);
    }

    /**
     * Adding a line of text to this file's contents
     * @param line String to add
     */
    public void add(String line) {
        if(line.contains("~")) { // stop adding characters before "~"
            int index = line.indexOf("~");
            contents.add(line.substring(0, index));
        } else {
            contents.add(line);
        }
    }

    /**
     * Prints all contents of file.
     */
    public void read() {
        for(String e : contents) {
            System.out.println(e);
        }
    }

    /**
     * Counts characters to form a size in bytes of the file.
     * @return long bytes
     */
    public long getSize() {
        long size = 0;
        for(String line : contents) {
            size += line.length();
        } return size;
    }
    @Override
    public String toString() {
        return this.name;
    }
}