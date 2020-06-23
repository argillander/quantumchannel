import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Channel
{
    DatagramSocket in;
    DatagramSocket out;

    int targetPort;
    int inPort;

    Gson gson;

    Class thisclass;

    ChannelRecipient recipient;

    public Channel(ChannelRecipient recipient, int inport, int targetPort, Class cls) {
        try {
            this.inPort = inport;
            this.targetPort = targetPort;
            this.recipient = recipient;
            this.thisclass = cls;
	    in = new DatagramSocket(inport);
	    out = new DatagramSocket();

	    this.gson = new Gson();
	}catch (IOException e){
            e.printStackTrace();
	}

	startReceive();
    }

    private String transmittableToJsonString(Transmittable m){
	JSONObject obj = new JSONObject();
	String str = gson.toJson(m);
	System.out.println(str);
	return str;
    }

    private Transmittable jsonStringToTransmittable(String str){
//	Type type = new TypeToken<Channel<T>>() {}.getType();
	JsonReader reader = new JsonReader(new StringReader(str));
	reader.setLenient(true);
	return gson.fromJson(reader, thisclass);
    }


    private void startReceive(){
	new Thread(() -> {
	    while(true) {
		byte[] buf = new byte[65536];

		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		try {
		    in.receive(packet);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		Transmittable m =  jsonStringToTransmittable(new String(buf));
		System.out.println(m);
	    }

	}).start();


    }



    public void send(Transmittable m){
	try {
	    String msg = transmittableToJsonString(m);
	    out.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), targetPort));

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }


    public static void main(String[] args) {
	Channel a = new Channel(null, 5555, 5556, PolarizationQubit.class);
	Channel b = new Channel(null, 5556, 5555, PolarizationQubit.class);
	a.send(new PolarizationQubit(Polarization.P));
    }


}
