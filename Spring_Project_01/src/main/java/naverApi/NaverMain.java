package naverApi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NaverMain {

	public static void main(String[] args) {
        String[] categories = {
            "건강", "공예", "농구", "야구", "축구", "수영", 
            "유럽여행", "일본여행", "중국요리", 
            "다이어트 요리", "사찰요리", "생활요리", "서양음악", "재즈"
        };

        for (String category : categories) {
            fetchDataAndSaveToDatabase(category);
            System.out.println();
            System.out.println(" -> "+category+" 카테고리의 큐레이팅 저장 성공!!");
            System.out.println();
            System.out.println();
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println();
            System.out.println();
        }
        System.out.println(" [ 큐레이팅 저장이 종료됩니다 ]");
        System.out.println();
        System.out.println();
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }

    private static void fetchDataAndSaveToDatabase(String category) {
        NaverAPIUrl naverAPIUrl = new NaverAPIUrl(category);

        String apiURL = naverAPIUrl.getUrl();
        String clientId = naverAPIUrl.getClientId();
        String clientSecret = naverAPIUrl.getClientSecret();

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        System.out.println("API 응답 데이터 (" + category + "): " + responseBody); // 응답 데이터 출력

        List<NaverDTO> products = parseXml(responseBody, category);

        NaverDAO dao = new NaverDAO();
        for (NaverDTO product : products) {
            System.out.println("Inserting product: " + product.getProduct_name() + " (Category: " + category + ")");
            dao.insertProduct(product);
        }

        // 로그를 출력하여 데이터베이스에 저장된 데이터를 확인
        List<NaverDTO> savedProducts = dao.findAll();
        for (NaverDTO product : savedProducts) {
            System.out.println("Category: " + product.getCuration_category());
            System.out.println("Product Name: " + product.getProduct_name());
            System.out.println("Product URL: " + product.getProduct_url());
            System.out.println("Product Pic: " + product.getProduct_pic());
            System.out.println();
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("API 요청과 응답 실패");
            return null;
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

    private static List<NaverDTO> parseXml(String xml, String category) {
        List<NaverDTO> products = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                NaverDTO nDTO = null;
                StringBuilder content = new StringBuilder();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("item")) {
                        nDTO = new NaverDTO();
                        nDTO.setCuration_category(category); // category 설정
                    }
                    content.setLength(0);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (nDTO != null) {
                        switch (qName.toLowerCase()) {
                            case "title":
                                nDTO.setProduct_name(content.toString());
                                break;
                            case "link":
                                nDTO.setProduct_url(content.toString());
                                break;
                            case "image":
                                nDTO.setProduct_pic(content.toString());
                                break;
                            case "item":
                                products.add(nDTO);
                                break;
                        }
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    content.append(new String(ch, start, length));
                }
            };

            saxParser.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")), handler);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("XML 파싱 오류");
        }
        return products;
    }
}
