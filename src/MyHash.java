import java.util.HashSet;
import java.util.Iterator;

/**
* @����: MyHash
* @����: ʵ�ֶ������Ĺ�ϣ�洢��������hash��ͻ
* @���ߣ� LDC
* @���ڣ� Dec 20, 2013 16:52:39 PM
*/
public class MyHash {//HashSet�ײ� ��HashMapʵ�֣��Ѿ�ʵ���������������hash��ͻ
	private int size = 0;//hash������
	private int primes = 1;//ģ
	
	public MyHash(int size){//���캯��	
		this.size = size;
		this.primes = this.getPrimes();
	}
	
	private class PackInt{//ͨ���ڲ����װ���洢���ݣ���ʵ��hash����
		private int data;
		
		public PackInt(int data){//���캯��
			this.data = data;
		}
		
		public int getData(){
			return this.data;
		}
		
		public boolean equals(int test){//��дequals����
			if(this.data == test){
				return true;
			}else{
				return false;
			}
		}
		
		public int hashCode(){//��дhashCode����
			return data % primes;
		}
	}
	
	private boolean isPrimes(int test){//�ж�һ�������Ƿ�Ϊ����
		for(int i=test-1;i>1;i--){
			if(test % i == 0){
				return false;
			}
		}
		return true;
	}
	
	private int getPrimes(){//��ȡ��Ӧnum����ģ�õ�����
		for(int i=size;i>1;i--){
			if(isPrimes(i)){
				System.out.println("������"+i);
				return i;
			}
		}
		return 1;
	}
	
	public HashSet<PackInt> getHash(int initialCapacity, float loadFactor){//��þ���ָ���ĳ�ʼ������ָ���ļ������ӵĿ�HashSet
		HashSet<PackInt> hash = new HashSet<>(initialCapacity, loadFactor);
		
		return hash;
	}
	
	
	public static void main(String[] args){
		int[] data = {1,2,3,4,5,6,14,13,12,11,100,23,65,154};//�򵥻�ȡԭʼintԪ��
		float loadFactor = 0.75f;//ָ����������
		int initialCapacity = (int)(data.length/loadFactor)+1;//����Ҫ�㹻����ֹrehashing
		
		MyHash myHash = new MyHash(initialCapacity);
		HashSet<PackInt> hash = myHash.getHash(initialCapacity, loadFactor);//������ϣ��
		
		System.out.println();
		System.out.println("ԭʼintԪ�أ�");
		for(int i=0;i<data.length;i++){//HashSet�ײ� ��HashMapʵ�֣��Ѿ�ʵ���������������hash��ͻ
			System.out.print(data[i]+"  ");
			hash.add(myHash.new PackInt(data[i]));//��װintΪPackInt���ͣ�Ȼ����ӵ���ϣ����
		}
		System.out.println();
		System.out.println();
		System.out.println("���������ϣ����PackIntʵ�������е�data���ݺͶ�Ӧ��hashcodeֵ��");
		Iterator it = hash.iterator();
		while(it.hasNext()){
			PackInt packInt = (PackInt) it.next();
			System.out.print(packInt.getData()+":"+packInt.hashCode()+"  ");
		}
		System.out.println();
	}
}
