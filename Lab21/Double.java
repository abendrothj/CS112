
class Node {
	private final char m_val;
	Node prev;
	Node next;

	Node(char c) { m_val = c; prev = null; next = null; }
	public char val() { return m_val; }
	@Override
	public String toString() {
		return String.valueOf(m_val);
	}
}


class DoubleList {
	Node m_head;
	Node m_current;

	DoubleList() { m_head = null; m_current = null; }
	
	// Reset list so current points to HEAD.
	Node head() { m_current = m_head; return m_current; }

	// Return current node
	Node current() { return m_current; }
	
	// Return node after current
	Node peek() {
		if (m_current == null) { return null; }
		return m_current.next;
	}

	// Advance 'current' to the following node.
	// If next() called repeatedly, current will eventually == null,
	// after moving past the end of the list
	Node next() {
		Node retval = m_current;
		if (m_current != null) {
			m_current = m_current.next;
		}
		return retval;
	}
	
	// add()
	//
	// Put 'n' after current and before current.next .
	// Make 'n' the new 'current' after the insertion.
	// Be sure to handle cases of empty list, current == head, current == last .
	//
	// Throw an IndexOutOfBoundsException if user adds when 
	// list is nonempty but current == null
	//
	// STUDENTS FILL IN HERE.
	void add(Node n) {
		if(m_head == null && m_current == null) {
			m_head = n;
			m_current = n;
		}
		else if(m_current == null) {
			throw new IndexOutOfBoundsException("ERROR: current is null");
		} else if(m_current == m_head) {
			n.prev = m_current;
			n.next = m_current.next;
			if(m_current.next != null) {
				m_current.next.prev = n;
			}
			m_current.next = n;
			m_current = n;
        } else {
			if(m_current.next != null) {
				n.next = m_current.next;
				n.next.prev = n;
			}
			n.prev = m_current;
			m_current.next = n;
			m_current = n;
		}

	}

	void add(char c) { add(new Node(c)); }

	// remove()
	//
	// Remove current node, having current point to the predecessor of the old 'current'.
	// Be sure to handle cases of empty lists, current == head, current == last .
	//
	// Throw an IndexOutOfBoundsException if user removes when current == null
	//
	// STUDENTS FILL IN HERE.
	void remove() {
		if(m_current == null) {
			throw new IndexOutOfBoundsException("ERROR: current is null");
		} if(m_current == m_head) {
			m_head = m_current.next;
			if (m_current.next != null) {
				m_current.next.prev = null;
			}
			m_current = m_head;
        } else {
			if(m_current.next != null) {
				m_current.next.prev = m_current.prev;
			} if (m_current.prev != null) {
				m_current.prev.next = m_current.next;
			}
			m_current = m_current.prev;
		}
	}
} // end class DoubleList


// class Double
//
// contains main() function, which exercises class DoubleList
public class Double {

	// PrintList()
	//
	// Print all char values in the list 'd' .
	// Print a '<' after the value that corresponds to the 'current'
	// element of 'd'.
	//
	// STUDENTS FILL IN HERE.
	static void PrintList(DoubleList d) {
		Node current = d.head();
		while(current != null) {
			System.out.print(current.toString());
			if(current == d.current()) {
				System.out.print("<");
			}
			current = d.next();
		}
	}

	public static void main(String[] args) {
		DoubleList list = new DoubleList();
		// Put the letters 'a' through 'z' into the list
		for(char c = 'a'; c <= 'z'; c++) {
			list.add(c);
		}
		
		// Go through a pattern of list operations
		list.head();
		list.add('!');
		list.head();
		list.remove();
		list.next();
		list.remove();
		list.next();
		list.remove();
		list.add('@');
		list.next();
		list.next();
		list.add('#');
		list.remove();
		list.next();
		list.add('$');
		list.next();
		list.remove();
		list.next();
		list.next();
		list.next();
		list.next();
		list.next();
		list.next();
		list.remove();
		list.next();
		list.next();
		
		// Output the final list
		PrintList(list);
	} // end main()

    public static double parseDouble(String arg) { // Programs in project were not running without a pointer
		return java.lang.Double.parseDouble(arg);
    }
} // end Double