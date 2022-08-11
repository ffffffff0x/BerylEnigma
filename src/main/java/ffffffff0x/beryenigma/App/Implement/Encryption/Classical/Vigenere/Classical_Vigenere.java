package ffffffff0x.beryenigma.App.Implement.Encryption.Classical.Vigenere;

public  class Classical_Vigenere {
    public static String encrypt(String OriginalText, String Key)
    {
        char[] oall = OriginalText.toCharArray();
        char[] keyall = Key.toUpperCase().toCharArray();
        StringBuilder CipherText = new StringBuilder();

        for(int i = 0; i<OriginalText.length(); i++)
        {
            int osingle = oall[i];
            int keysingle = keyall[i % Key.length()];

            if(osingle >= 65 && osingle <= 90)
            {
                if(osingle+(keysingle-65)<=90)
                {
                    char c = (char)(osingle+(keysingle-65));
                    CipherText.append(c);
                }
                else
                {
                    char c = (char)(osingle+(keysingle-65)-26);
                    CipherText.append(c);
                }
            }
            else if(osingle >= 97 && osingle <= 122)
            {
                if(osingle+(keysingle-65)<=122)
                {
                    char c = (char)(osingle+(keysingle-65));
                    CipherText.append(c);
                }
                else
                {
                    char c = (char)(osingle+(keysingle-65)-26);
                    CipherText.append(c);
                }
            }
        }
        return CipherText.toString();
    }

    public static String decrypt(String EncryptedText, String Key)
    {
        char[] eall = EncryptedText.toCharArray();
        char[] keyall = Key.toUpperCase().toCharArray();
        StringBuilder DecryptedText = new StringBuilder();

        for(int i = 0; i<EncryptedText.length(); i++)
        {
            int esingle = eall[i];
            int keysingle = keyall[i % Key.length()];

            if(esingle >= 65 && esingle <= 90)
            {
                if(esingle-(keysingle-65)>=65)
                {
                    char d = (char)(esingle-(keysingle-65));
                    DecryptedText.append(d);
                }
                else
                {
                    char d = (char)(esingle-(keysingle-65)+26);
                    DecryptedText.append(d);
                }
            }
            else if(esingle >= 97 && esingle <= 122)
            {
                if(esingle-(keysingle-65)>=97)
                {
                    char d = (char)(esingle-(keysingle-65));
                    DecryptedText.append(d);
                }
                else
                {
                    char d = (char)(esingle-(keysingle-65)+26);
                    DecryptedText.append(d);
                }
            }
        }
        return DecryptedText.toString();
    }
}
