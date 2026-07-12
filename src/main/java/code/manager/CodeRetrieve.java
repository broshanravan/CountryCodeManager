package code.manager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class CodeRetrieve {

    private final HttpClient httpClient;

    public CodeRetrieve(){
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getHighersCodeFromCollection(List<String> contryCodesList){
        String answer = "0";
        try {
            for(String numStr:contryCodesList ){
                numStr = numStr.
                        replaceAll("\"","")
                        .trim();
                if(Integer.parseInt(numStr)>Integer.parseInt(answer)){
                    answer = numStr;
                }
            }
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }

        return answer;

    }

    Logger logger = Logger.getLogger(CodeRetrieve.class);

    public String getHighest(String CountryName){
        String username = "Bruce";
        String pasword = "Pasargad2536";
        String baseUrl = "http://localhost:8081/api/get/country/code/by/name?countryName=" + CountryName;
        URI uri = URI.create(baseUrl);

        HttpClient client =  HttpClient.newBuilder().authenticator(
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication( ) {
                        return new PasswordAuthentication(username, pasword.toCharArray());
                    }
                }

        ).build();


        HttpRequest request =HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();


        List<String> contryCodesList = new LinkedList<String>();


        String answer = "0";

        try {
            HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body().substring(1);
            responseBody = responseBody.replaceAll("[-+.^]","");
            responseBody = responseBody.replaceAll("]","");
            String[] codesArray = responseBody.split(",");
            contryCodesList =Arrays.stream(codesArray).collect(Collectors.toList());
            answer = getHighersCodeFromCollection(contryCodesList);

        }catch (NumberFormatException | IOException | InterruptedException nfe) {
            logger.error("");
        }

        return answer;

    }




    public static void main(String[] args) {
        CodeRetrieve codeRetrieve = new CodeRetrieve();

        String code = codeRetrieve.getHighest("UK");

        System.out.println("The latest code is: " + code);


    }

}
