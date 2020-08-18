package javaTesting;

public class AustralianTraffic implements CentralTraffic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	AustralianTraffic a = new AustralianTraffic();
		CentralTraffic a = new AustralianTraffic();
		a.redStop();
		a.flashYellow();
		a.greenGo();	
		
		AustralianTraffic at = new AustralianTraffic();
		at.walkon();

	}

	@Override
	public void greenGo() {
		// TODO Auto-generated method stub
		System.out.println("Green Go implementation ");
	}

	@Override
	public void redStop() {
		// TODO Auto-generated method stub
		System.out.println("Red stop implementation ");
	}

	@Override
	public void flashYellow() {
		// TODO Auto-generated method stub
		System.out.println("flashing Yellow implementation ");
		
	}
	
	public void walkon() {
		System.out.println("walk on method imlementation ");
	}

}
