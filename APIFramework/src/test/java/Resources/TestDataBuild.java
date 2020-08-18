package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddNewPlace;

public class TestDataBuild {
	public AddNewPlace addPlacePayload(String name , String language, String address) {
		pojo.AddNewPlace p = new pojo.AddNewPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		List <String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);

		pojo.Location l = new pojo.Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);


		return(p);
	}

	public String deletePlacePayload(String place_Id) {
		
		return ("{\r\n    \"place_id\":\""+place_Id+"\"\r\n}\r\n");
	}
}
