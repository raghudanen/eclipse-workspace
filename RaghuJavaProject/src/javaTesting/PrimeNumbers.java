package javaTesting;

public class PrimeNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num =13;
		boolean prime = true;
		for (int i=2; i< num;i++) {
			if (num%i == 0) {
				prime = false;
				break;
			}

		}
		if (prime == false)System.out.println("The number " + num+ " is not prime");
		else System.out.println("The number " + num+ " is prime");
	}

}
