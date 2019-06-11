package ch.mifehr.text_to_mp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

public class MainAppliaction {

	private static String TEXT = "";

	private static String MP3_DATEI_NAME = "";

	private static final String API_KEY = "e83ef1016e6f4241a73fb9bde725251d";

	public static void main(String[] args) throws Exception {

		File folder = new File("/Users/mifehr/Projects/workspace/txt_to_mp3_2/inputfiles");
		for (File file : folder.listFiles()) {
			System.out.println(file.getName());

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String intent;

				while ((intent = br.readLine()) != null) {
					System.out.println(intent);
					TEXT = intent;
					MP3_DATEI_NAME = file.getName() + ".mp3";
					

				}
			

			} catch (IOException e) {
				e.printStackTrace();
			}
			textToMp3(TEXT, MP3_DATEI_NAME);
		}

		

	}

	private static void textToMp3(String text, String dateiname) throws Exception {
		VoiceProvider tts = new VoiceProvider(API_KEY);

		VoiceParameters params = new VoiceParameters(text, Languages.German);
		params.setCodec(AudioCodec.MP3);
		params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_mono);
		params.setBase64(false);
		params.setSSML(false);
		params.setRate(0);

		byte[] voice = tts.speech(params);

		FileOutputStream fos = new FileOutputStream("./outputs/" + dateiname);
		fos.write(voice, 0, voice.length);
		fos.flush();
		fos.close();
		
	}

}