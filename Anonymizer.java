// program which changes numbers into string code
import java.util.*;
public class Anonymizer implements AnonymizerInterface{
	
	Set<String> setOfUniqueStrings = new HashSet<String>();
	Map<String, String> mapOfUsedPairs = new HashMap<String, String>();
	
	public static String stringSetCheck(Collection<String> set, String s){
		if((s!="") && set.contains(s)){
			s = Helper.generate();
			stringSetCheck(set, s);
		}	return s;
	}
	
	public List<PhoneInterface> anonimize(List<PhoneInterface> phones) {
		if(phones!=null){
			for(PhoneInterface contact : phones){
				if(contact != null){
					String originalNum = contact.getPhoneNumber();
					String generatedStr = Helper.generate();
					
					if(originalNum!=""){						
						if(!(mapOfUsedPairs.containsKey(originalNum))){
							//mapOfUsedPairs.put(originalNum, "");
							
							if(mapOfUsedPairs.containsValue(generatedStr)) generatedStr = stringSetCheck(mapOfUsedPairs.values(), generatedStr);							
	
							contact.setPhoneNumber(generatedStr);
							mapOfUsedPairs.put(originalNum, generatedStr);
						} else contact.setPhoneNumber(mapOfUsedPairs.get(originalNum));											
					}
				}
			}
		}
		return phones;
	}

	public Set<String> getUsedStrings() {
		return mapOfUsedPairs.keySet();
	}
	
	public Map<String, String> getMapping() {
		return mapOfUsedPairs;
	}
	 
	public static void main(String[] args){
		Anonymizer a = new Anonymizer();
		List<PhoneInterface> l = new ArrayList<PhoneInterface>();
		l.add(new Contact(0, "123"));
		l.add(new Contact(1, ""));
		l.add(new Contact(2, "123"));
		l.add(new Contact(3, null));
		l.add(new Contact(4, "12553"));
		
		a.anonimize(l);
		
	   for (Iterator i = a.getMapping().keySet().iterator(); i.hasNext(); ) {
	       String key = (String) i.next();
	       String value = (String) a.getMapping().get(key);
	       System.out.println(key + " = " + value);
	   }
	}
}