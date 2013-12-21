import java.util.HashSet;
import java.util.Iterator;

/**
* @类名: MyHash
* @描述: 实现对整数的哈希存储，并处理hash冲突
* @作者： LDC
* @日期： Dec 20, 2013 16:52:39 PM
*/
public class MyHash {//HashSet底层 以HashMap实现，已经实现了用拉链法解决hash冲突
	private int size = 0;//hash表容量
	private int primes = 1;//模
	
	public MyHash(int size){//构造函数	
		this.size = size;
		this.primes = this.getPrimes();
	}
	
	private class PackInt{//通过内部类封装待存储数据，并实现hash函数
		private int data;
		
		public PackInt(int data){//构造函数
			this.data = data;
		}
		
		public int getData(){
			return this.data;
		}
		
		public boolean equals(int test){//重写equals方法
			if(this.data == test){
				return true;
			}else{
				return false;
			}
		}
		
		public int hashCode(){//重写hashCode方法
			return data % primes;
		}
	}
	
	private boolean isPrimes(int test){//判断一个数字是否为素数
		for(int i=test-1;i>1;i--){
			if(test % i == 0){
				return false;
			}
		}
		return true;
	}
	
	private int getPrimes(){//获取相应num的求模用的素数
		for(int i=size;i>1;i--){
			if(isPrimes(i)){
				System.out.println("质数："+i);
				return i;
			}
		}
		return 1;
	}
	
	public HashSet<PackInt> getHash(int initialCapacity, float loadFactor){//获得具有指定的初始容量和指定的加载因子的空HashSet
		HashSet<PackInt> hash = new HashSet<>(initialCapacity, loadFactor);
		
		return hash;
	}
	
	
	public static void main(String[] args){
		int[] data = {1,2,3,4,5,6,14,13,12,11,100,23,65,154};//简单获取原始int元素
		float loadFactor = 0.75f;//指定加载因子
		int initialCapacity = (int)(data.length/loadFactor)+1;//容量要足够，防止rehashing
		
		MyHash myHash = new MyHash(initialCapacity);
		HashSet<PackInt> hash = myHash.getHash(initialCapacity, loadFactor);//建立哈希表
		
		System.out.println();
		System.out.println("原始int元素：");
		for(int i=0;i<data.length;i++){//HashSet底层 以HashMap实现，已经实现了用拉链法解决hash冲突
			System.out.print(data[i]+"  ");
			hash.add(myHash.new PackInt(data[i]));//封装int为PackInt类型，然后添加到哈希表中
		}
		System.out.println();
		System.out.println();
		System.out.println("迭代输出哈希表中PackInt实例对象中的data数据和对应的hashcode值：");
		Iterator it = hash.iterator();
		while(it.hasNext()){
			PackInt packInt = (PackInt) it.next();
			System.out.print(packInt.getData()+":"+packInt.hashCode()+"  ");
		}
		System.out.println();
	}
}
