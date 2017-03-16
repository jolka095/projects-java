// programme about exceptions
public class HDDIO implements HDDIOInterface {
	HDDInterface disc;

	@Override
	public void setHDD(HDDInterface hddInterface) {
		disc = hddInterface;
	}
	
	@Override
	public void writeAndTest(int sector, short value) throws BadSectorException, DataCorruptionException, DiskTooHotException {
		boolean brokenDown = false;
		
		try {
			if(!brokenDown)	disc.write(sector, value);

			if(!brokenDown){
				for(int j = 0; j<NUMBER_OF_ATTEMPTS; j++){
					if(disc.read(sector)!=value){
						brokenDown = true; // if broken - exit loop
						throw new DataCorruptionException();
					}
				}
			}
		} catch (BadSectorException e) {
			brokenDown = true;
			throw e;
		} catch (DiskTooHotException e) {
			TimeHelper.sleepThread(e.getCoolingTime());
			this.writeAndTest(sector, value);
			throw e;
		}
	}
}

class HDDTest implements HDDTestInterface {
	int numberOfBadSectorsEvents = 0;
	int numberOfDataCorruptionEvents = 0;
	int numberOfDiscTooHotEvents = 0;
	
	@Override
	public void setHDD(HDDInterface hddInterface) {
		
		HDDIO klasa1 = new HDDIO();
		klasa1.setHDD(hddInterface); // setting disc

	 // testing write&read for every sector
		for(int i = 0; i< hddInterface.getNumberOfSectors();i++){

			boolean brokenDown = false;
			try {
				if(!brokenDown)	klasa1.writeAndTest(i, (short) 23);			
			} catch (BadSectorException e) {
				numberOfBadSectorsEvents++;
				brokenDown = true;
			} catch (DataCorruptionException e) {
				numberOfDataCorruptionEvents++;
				brokenDown = true;
			} catch (DiskTooHotException e) {
				numberOfDiscTooHotEvents++;
			}
		}
	}

	@Override
	public int getNumberOfBadSectorsEvents() {
		return numberOfBadSectorsEvents;
	}

	@Override
	public int getNumberOfDataCorruptionEvents() {
		return numberOfDataCorruptionEvents;
	}

	@Override
	public int getNumberOfDiscTooHotEvents() {
		return numberOfDiscTooHotEvents;
	}
}