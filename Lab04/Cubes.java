/** Class that prints the cube result starting at 1,
*  printing everything below 2000.
*
*/
class Cubes {
	public static void main(String[] args) {
		int num = 1;
		while(num*num*num < 2000) {
			System.out.println(num*num*num);
			num++;
		}
	}
}