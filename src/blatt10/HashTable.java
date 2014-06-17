package blatt10;


import java.util.LinkedList;
class HashTable {
	private LinkedList<HashItem>[] array;
	private String hashingVerfahren;
	@SuppressWarnings("unchecked")
	public HashTable(int hashSize, String hashing) {
	   	array = new LinkedList[hashSize];
	   	for (int i = 0; i<hashSize; i++) {
	   		array[i] = new LinkedList<HashItem>();
	   	}
	   	hashingVerfahren = new String(hashing);
	}
	public long hashCode (String str) {
		if (hashingVerfahren.equals("RSHash")) {
			return GeneralHashFunctionLibrary.RSHash(str);
		}else 
			return GeneralHashFunctionLibrary.JSHash(str);
	}
		 
	public void put(String key) {
		int hash = Math.abs((int)hashCode(key));
		hash = hash % array.length;
		if (get(key) != null ) {
			get(key).setInfo(get(key).getInfo()+1);
		}else {
			array[hash].add(new HashItem(key,1));
		}
	}
	
	public HashItem get(String key) {
		int hash = Math.abs((int)hashCode(key));
		hash = hash % array.length;
		for (int i = 0; i<array[hash].size(); i++) {
			if (array[hash].get(i).getKey().equals(key)) {
				return array[hash].get(i);
			}
		}
		return null;
	}
	public void clear() {
		for (int i = 0; i<array.length; i++) {
			array[i].clear();
		}
	}
	public int numberOfCollisions() {
		int count = 0;
		for (int i = 0; i<array.length; i++) {
			if (array[i].size()>0) {
				count = count + array[i].size()-1;
			}
		}
		return count;
	}
	public void printHashTable() {
		System.out.println("***************************************");
		System.out.println();
		for (int i = 0; i<array.length; i++) {
			System.out.println("---------------------------------------");
			System.out.println("Hash items with hash value "+i+":");
			for (int j = 0; i<array[i].size(); i++) {
				System.out.println("	key: "+array[i].get(j).getKey()+"   -- info: "+array[i].get(j).getInfo());
			}
		}
		System.out.println("---------------------------------------");
		System.out.println();
		System.out.println("The number of collisions is "+numberOfCollisions());
		System.out.println();
		System.out.println("***************************************");
	}
}
