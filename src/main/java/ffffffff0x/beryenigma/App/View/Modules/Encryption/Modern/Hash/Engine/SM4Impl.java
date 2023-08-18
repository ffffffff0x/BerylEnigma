package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Hash.Engine;

import org.apache.commons.codec.binary.Base64;

import ffffffff0x.beryenigma.Kit.Utils.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Impl {
	public String secretKey = "";
	
	public String iv = "";
	
	public boolean hexString = false;
	
	public SM4Impl() {
	}
	
	public String encryptData_ECB(String plainText) {
		try 
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_ENCRYPT;
			
			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = Util.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("GBK"));
			String cipherText = Base64.encodeBase64String(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0)
			{
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String decryptData_ECB(String cipherText) {
		try 
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_DECRYPT;
			
			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = Util.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.decodeBase64(cipherText));
			return new String(decrypted, "GBK");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String encryptData_CBC(String plainText) {
		try 
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_ENCRYPT;
			
			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString)
			{
				keyBytes = Util.hexStringToBytes(secretKey);
				ivBytes = Util.hexStringToBytes(iv);
			}
			else
			{
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("GBK"));
			String cipherText = Base64.encodeBase64String(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0)
			{
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String decryptData_CBC(String cipherText) {
		try 
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_DECRYPT;
			
			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString)
			{
				keyBytes = Util.hexStringToBytes(secretKey);
				ivBytes = Util.hexStringToBytes(iv);
			}
			else
			{
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes,Base64.decodeBase64(cipherText));
			return new String(decrypted, "GBK");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/*
	public static void main(String[] args) throws IOException 
	{
		String plainText = "abcd";
		
		SM4Utils sm4 = new SM4Utils();
		sm4.secretKey = "JeF8U9wHFOMfs2Y8";
		sm4.hexString = false;
		
		System.out.println("ECB model");
		String cipherText = sm4.encryptData_ECB(plainText);
		System.out.println("ciphertext: " + cipherText);
		System.out.println("");
		
		plainText = sm4.decryptData_ECB(cipherText);
		System.out.println("plaintext: " + plainText);
		System.out.println("");
		
		System.out.println("CBC model");
		sm4.iv = "UISwD9fW6cFh9SNS";
		cipherText = sm4.encryptData_CBC(plainText);
		System.out.println("ciphertext: " + cipherText);
		System.out.println("");
		
		plainText = sm4.decryptData_CBC(cipherText);
		System.out.println("plaintext: " + plainText);
	}
	*/
}

