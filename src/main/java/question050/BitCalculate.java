package question050;

public abstract class BitCalculate {
	
	public static void main(String[] args) {
	
		byte b1 = -128; // 1000,0000
		byte b2 = -128;
		byte result = (byte) (b1+b2);
		System.out.println(result); // 0  1,0000,0000�����һλȥ������0000,0000
		
		int i1 = Integer.MAX_VALUE; // 0111,1111,1111,1111,1111,1111,1111,1111
		int i2 = Integer.MAX_VALUE;
		System.out.println(i1+i2); // -2   1111,1111,1111,1111,1111,1111,1111,1110 -2�Ķ����Ʊ�ʾ
	}

}
