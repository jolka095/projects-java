// program which changes objects into string code
import java.lang.reflect.*;
import java.util.*;

public class ObjectAnonymizer implements ObjectAnonymizerInterface{
			
	Set<String> setOfUniqueStrings = new HashSet<String>();
	Map<String, String> mapOfUsedPairs = new Hashtable<String, String>();
	
	public static String stringSetCheck(Collection <String> set, String s){
		if((s!="") && set.contains(s)){
			s = Helper.generate();
			stringSetCheck(set, s);
		}	return s;
	}
	
	@Override
	public List<Object> anonimize(List<Object> objects) {
		if(objects!=null){
			
			for (int i = 0; i < objects.size(); i++) {			
				Object obj = objects.get(i);				
				if(obj != null){					
													
					for (Field field : obj.getClass().getFields()) {
						
						if(field.getType().isAssignableFrom(String.class) && field.isAnnotationPresent(ToAnonimize.class)){
							
							ToAnonimize a = (ToAnonimize)field.getAnnotation(ToAnonimize.class);
							if(a.readyToAnonimize()){
									
								try {
									if(field.get(obj) != null){
									
										field.setAccessible(true);
										String generatedStr = Helper.generate();
										Object valueOfField = field.get(obj);
										
										if(valueOfField != ""){ // empty string will not change
											if(mapOfUsedPairs.size()>0){
												if(!(mapOfUsedPairs.containsKey(valueOfField.toString()))){													
													if(mapOfUsedPairs.containsValue(generatedStr)) generatedStr = stringSetCheck(mapOfUsedPairs.values(), generatedStr);

													field.set(obj, generatedStr);
													mapOfUsedPairs.put(valueOfField.toString(), generatedStr);	

												} else field.set(obj, mapOfUsedPairs.get(valueOfField.toString()));																							
											} else { // when map is empty
												field.set(obj, generatedStr);
												mapOfUsedPairs.put(valueOfField.toString(), generatedStr);
											}	
											setOfUniqueStrings.add(generatedStr);
										}
									}										
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}					
			        }
				}
			}
		}
		return objects;
	}

	@Override
	public Set<String> getUsedStrings() {		
		return setOfUniqueStrings; 
	}

	@Override
	public Map<String, String> getMapping() { 
		return mapOfUsedPairs; 
	}
	
	public static void main(String[] args){
		
		List<Object> lista = new ArrayList<Object>();
		
		lista.add(new ExampleClass());
		
		ExampleClass e = new ExampleClass();
		e.surname = "Filipiak";
		lista.add(e);
		
		ExampleClass e2 = new ExampleClass();
		e2.surname = "";
		lista.add(e2);
		
		ExampleClass e1 = new ExampleClass();
		e1.surname = "Kowalski";
		lista.add(e1);
		
		lista.add(new ExampleClass());
		
		ExampleClass e3 = new ExampleClass();
		e3.surname = "";
		lista.add(e3);
		
		lista.add(new ExampleClass());
		

		ObjectAnonymizer o = new ObjectAnonymizer();
		System.out.println("Lista: (rozmiar = " + lista.size() + ")");
		for(int i = 0; i<lista.size(); i++){
			System.out.println("\t*" + lista.get(i));
		}
		o.anonimize(lista);
				
		System.out.println("Set:");
		for(String s: o.getUsedStrings()){
			System.out.println(s);
		}
		
		System.out.println("Mapa:");
		for (Iterator<?> i = o.getMapping().keySet().iterator(); i.hasNext(); ) {
		       String key = (String) i.next();
		       String value = (String) o.getMapping().get(key);
		       System.out.println(key + " = " + value);
		}
	}
}