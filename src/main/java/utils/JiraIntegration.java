package utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JiraIntegration {

    public static final String JIRA_BASE_URL = "https://mohammadfaheljira.atlassian.net";
    private static final String API_ENDPOINT = "/rest/api/2/issue";
    private static final String AUTH_TOKEN = "Basic bW9oYW1tYWRmYWhlbC4yMDAwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBXTzJPdmxSZ3hrRzNXbGEyNkVrZWNpUV9BSEd1VWZ4QmZlZzhVNUVPcUREMDNYbUp1bWw1YTl1b3Y1cmYtemRDWl9pSzdPVmdURjd2LUV2dEtMbEthRmlSLWRWNWJhM01OT3RNWEZrZUZISTBXdzNnV2RqOVdHNDdHNGlTWjMzTnRPVklZOHZTQm5lWEc1cGV2UmxQVEpwanJxSHQ2bk82Qk9Ld3ZKR2NxRFU9RUVEQzU3RTQ=";
    private static final String PROJECT_KEY = "ECS";

    public static String createIssue(String summary, String description) {
        try {
            System.out.println("🔄 [JIRA] Creating issue...");
            System.out.println("📝 [JIRA] Summary: " + summary);
            System.out.println("📝 [JIRA] Description: " + description);

            URL url = new URL(JIRA_BASE_URL + API_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", AUTH_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");

            String escapedSummary = summary.replace("\"", "\\\"").replace("\n", "\\n");
            String escapedDescription = description.replace("\"", "\\\"").replace("\n", "\\n");
            String jsonPayload = "{" +
                    "\"fields\": {" +
                    "\"project\": {\"key\": \"" + PROJECT_KEY + "\"}," +
                    "\"summary\": \"" + escapedSummary + "\"," +
                    "\"description\": \"" + escapedDescription + "\"," +
                    "\"issuetype\": {\"name\": \"Bug\"}" +
                    "}" +
                    "}";
            System.out.println("🔁 [JIRA] JSON Payload: " + jsonPayload);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            System.out.println("🔁 [JIRA] URL: " + url);
            System.out.println("🔁 [JIRA] Response code: " + responseCode);

            if (responseCode == 201) {
                System.out.println("✅ [JIRA] Issue created successfully!");
                try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
                    String response = scanner.useDelimiter("\\A").next();
                    // Extract ticket ID (e.g., "ECS-123") from response
                    int start = response.indexOf("\"key\":\"") + 7;
                    int end = response.indexOf("\"", start);
                    return response.substring(start, end);
                }
            } else {
                System.out.println("❌ [JIRA] Failed to create issue. Response code: " + responseCode);
                try (java.io.InputStream errorStream = conn.getErrorStream()) {
                    if (errorStream != null) {
                        Scanner s = new Scanner(errorStream).useDelimiter("\\A");
                        String response = s.hasNext() ? s.next() : "";
                        System.out.println("🔁 [JIRA] Error response: " + response);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ [JIRA] Exception occurred while creating issue.");
            e.printStackTrace();
        }
        return null;
    }
}