import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConversorMoedas {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/485c7fc0b6440be75dcb6d41/latest/";

    public static Map<String, Double> obterTaxasDeConversao(String moedaOrigem) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + moedaOrigem))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        TypeToken<Map<String, Double>> token = new TypeToken<>() {};
        return gson.fromJson(conversionRates, token.getType());
    }

    public static double converterMoeda(double valor, String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
        Map<String, Double> taxasDeConversao = obterTaxasDeConversao(moedaOrigem);
        double taxaOrigem = taxasDeConversao.get(moedaOrigem);
        double taxaDestino = taxasDeConversao.get(moedaDestino);

        // Calcula o valor convertido
        double valorConvertido = (valor / taxaOrigem) * taxaDestino;
        return valorConvertido;
    }
}
