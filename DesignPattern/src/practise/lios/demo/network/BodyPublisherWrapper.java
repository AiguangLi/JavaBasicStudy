package practise.lios.demo.network;

import jdk.incubator.http.HttpRequest.BodyPublisher;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author liaiguang
 * @date 2020/8/15
 */
public class BodyPublisherWrapper {
    public static BodyPublisher ofFormData(Map<Object, Object> data) {
        boolean first = true;
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first) {
                first = false;
            } else {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }

        return BodyPublisher.fromString(builder.toString());
    }

    public static BodyPublisher ofMimeMultipartData(Map<Object, Object> data, String boundary) throws IOException {
        ArrayList<byte[]> byteArrays = new ArrayList<>();
        byte[] separator = ("--" + boundary + "\nContent-Disposition: form-data;name=").getBytes();
        for(Map.Entry<Object, Object> entry : data.entrySet()) {
            byteArrays.add(separator);
            if (entry.getValue() instanceof Path) {
                Path path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                        + "\"\nContent-Type:" + mimeType + "\n\n").getBytes());
                byteArrays.add(Files.readAllBytes(path));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\n\n" + entry.getValue() + "\n").getBytes());
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes());

        return BodyPublisher.fromByteArrays(byteArrays);
    }

    public static BodyPublisher ofSimpleJSON(Map<Object, Object> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean first = true;
        for(Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first) {
                first = true;
            } else {
                builder.append(",");
            }
            builder.append(jsonEscape(entry.getKey().toString())).append(": ")
                    .append(jsonEscape(entry.getValue().toString()));
        }
        builder.append("}");

        return BodyPublisher.fromString(builder.toString());
    }

    private static Map<Character, String> replacements = Map.of(
            '\b', "\\b", '\f', "\\f",
            '\n', "\\n", '\r', "\\r",
            '\t', "\\t", '"', "\\\"", '\\', "\\\\");

    public static StringBuilder jsonEscape(String str) {
        StringBuilder result = new StringBuilder("\"");
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            String replacement = replacements.get(ch);
            if (replacement == null) {
                result.append(ch);
            } else {
                result.append(replacement);
            }
        }
        result.append("\"");
        return result;
    }
}