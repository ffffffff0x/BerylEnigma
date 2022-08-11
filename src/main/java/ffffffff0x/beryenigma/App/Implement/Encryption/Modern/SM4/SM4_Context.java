package ffffffff0x.beryenigma.App.Implement.Encryption.Modern.SM4;

public class SM4_Context {
	public int mode;
	
	public long[] sk;
	
	public boolean isPadding;

	public SM4_Context() 
	{
		this.mode = 1;
		this.isPadding = true;
		this.sk = new long[32];
	}
}
