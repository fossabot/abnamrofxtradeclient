package org.edinar.abnamrofxtradeclient;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.edinar.abnamrofxtradeclient.entities.ConversionCalculationRequest;
import org.edinar.abnamrofxtradeclient.entities.ConversionCalculationResponse;
import org.edinar.abnamrofxtradeclient.entities.IndicativeRate;
import org.edinar.abnamrofxtradeclient.entities.OrderRequest;
import org.edinar.abnamrofxtradeclient.entities.OrderRequestItem;
import org.edinar.abnamrofxtradeclient.entities.OrderResponse;
import org.edinar.abnamrofxtradeclient.entities.QuoteRequest;
import org.edinar.abnamrofxtradeclient.entities.QuoteRequestItem;
import org.edinar.abnamrofxtradeclient.entities.QuoteResponse;
import org.edinar.abnamrofxtradeclient.entities.Rate;
import org.edinar.abnamrofxtradeclient.entities.SwapPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * This test is disabled by default, because it makes remote requests to the
 * ABN AMRO sandbox environment. Remember to change the credentials in the
 * properties file before running this test.
 */
@Disabled
public class AbnAmroFxTradeClientTest {
    private static AbnAmroFxTradeClient client;

    @BeforeAll
    public static void setup() throws InterruptedException, IOException {
        client = new AbnAmroFxTradeClient(AbnAmroEnvironment.SANDBOX,
                                          new SimpleSecretManager());
        AbnAmroAccessToken accessToken = client.getAccessToken();
        Assertions.assertNotNull(accessToken);
        Assertions.assertEquals("3599", accessToken.getExpiresIn());
        Assertions.assertEquals("Bearer ey", accessToken.toString().substring(0, 9));
        Assertions.assertEquals(accessToken, client.getAccessToken(), "The current access token has NOT expired, but a new token was fetched.");
        accessToken.setExpiresIn("0"); // Simulate an expired token
        Assertions.assertNotEquals(accessToken, client.getAccessToken(), "The current token expired, but a new token was NOT fetched.");
    }

    @Test
    public void testGetAllowedCurrencyPairs() throws InterruptedException, IOException {
        Set<String> currencyPairs = client.getAllowedCurrencyPairs();
        Assertions.assertTrue(currencyPairs.contains("EURUSD"));
        Assertions.assertFalse(currencyPairs.contains("USDEUR"));
    }

    @Test
    public void testGetAllowedCurrencyPairsForClientAccount() throws InterruptedException, IOException {
        Set<String> currencyPairs = client.getAllowedCurrencyPairsBySettlementAccountGroup("Client Account");
        Assertions.assertTrue(currencyPairs.contains("EURGBP"));
        Assertions.assertTrue(currencyPairs.contains("EURJPY"));
        Assertions.assertTrue(currencyPairs.contains("EURUSD"));
        Assertions.assertFalse(currencyPairs.contains("GBPUSD"));
    }

    @Test
    public void testGetAllowedCurrencyPairsForHouseAccount() throws InterruptedException, IOException {
        Set<String> currencyPairs = client.getAllowedCurrencyPairsBySettlementAccountGroup("House Account");
        Assertions.assertTrue(currencyPairs.contains("GBPUSD"));
        Assertions.assertTrue(currencyPairs.contains("GBPJPY"));
        Assertions.assertTrue(currencyPairs.contains("USDCAD"));
    }

    @Test
    public void testGetSettlementAccountGroups() throws InterruptedException, IOException {
        Set<String> settlementAccountGroups = client.getSettlementAccountGroups();
        Assertions.assertTrue(settlementAccountGroups.contains("Client Account"));
        Assertions.assertTrue(settlementAccountGroups.contains("House Account"));
    }

    @Test
    public void testGetIndicativeRates() throws InterruptedException, IOException {
        Set<String> currencyPairs = Set.of("EURJPY", "EURGBP", "EURUSD");
        Set<IndicativeRate> indicativeRates = client.getIndicativeRates(currencyPairs, "ASAP");
        Assertions.assertEquals(3, indicativeRates.size());
        for (IndicativeRate indicativeRate : indicativeRates) {
            Assertions.assertNotNull(indicativeRate.getCurrencyPair());
            Rate spotRate = indicativeRate.getSpotRate();
            if (spotRate != null) {
                Assertions.assertNotNull(spotRate.getAskRate());
                Assertions.assertNotNull(spotRate.getBidRate());
                Assertions.assertNotNull(spotRate.getMidRate());
                Assertions.assertNotNull(spotRate.getEffectiveDateTime());
            }
            Rate allInRate = indicativeRate.getAllInRate();
            if (allInRate != null) {
                Assertions.assertNotNull(allInRate.getAskRate());
                Assertions.assertNotNull(allInRate.getBidRate());
                Assertions.assertNotNull(allInRate.getMidRate());
                Assertions.assertNotNull(allInRate.getEffectiveDateTime());
            }
            SwapPoints swapPoints = indicativeRate.getSwapPoints();
            if (swapPoints != null) {
                Assertions.assertNotNull(swapPoints.getAskPoints());
                Assertions.assertNotNull(swapPoints.getBidPoints());
            }
            Assertions.assertNotNull(indicativeRate.getSettlementDate());
        }
    }

    @Test
    public void testPostConversionCalculationsBuy() throws InterruptedException, IOException {
        ConversionCalculationRequest request = new ConversionCalculationRequest();
        request.setCalculationId(1L);
        request.setBuyCurrency("GBP");
        request.setBuyAmount(getRandomAmount());
        request.setSellCurrency("EUR");
        ConversionCalculationResponse response = client.postConversionCalculations(Set.of(request));
        Assertions.assertEquals(1, response.getConversions().size());
        Assertions.assertTrue(response.getFailures().isEmpty());
    }

    @Test
    public void testPostConversionCalculationsSell() throws InterruptedException, IOException {
        ConversionCalculationRequest request = new ConversionCalculationRequest();
        request.setCalculationId(2L);
        request.setBuyCurrency("GBP");
        request.setSellCurrency("USD");
        request.setSellAmount(getRandomAmount());
        ConversionCalculationResponse response = client.postConversionCalculations(Set.of(request));
        Assertions.assertEquals(1, response.getConversions().size());
        Assertions.assertTrue(response.getFailures().isEmpty());
    }

    @Test
    public void testPostConversionCalculationsInvalid() throws InterruptedException, IOException {
        ConversionCalculationRequest request = new ConversionCalculationRequest();
        request.setCalculationId(2L);
        request.setBuyCurrency("GBP");
        request.setBuyAmount(new BigDecimal("1000.00"));
        request.setSellCurrency("USD");
        request.setSellAmount(new BigDecimal("1000.00"));
        ConversionCalculationResponse response = client.postConversionCalculations(Set.of(request));
        Assertions.assertEquals(1, response.getFailures().size());
        Assertions.assertTrue(response.getConversions().isEmpty());
    }

    @Test
    public void testPostQuoteWithStandardTenor() throws InterruptedException, IOException {
        QuoteRequest quoteRequest = new QuoteRequest();
        QuoteRequestItem quoteRequestItem = new QuoteRequestItem();
        quoteRequestItem.setBuyCurrency("EUR");
        quoteRequestItem.setSellCurrency("USD");
        quoteRequestItem.setSellAmount(getRandomAmount());
        quoteRequestItem.setSettlement("ASAP");
        quoteRequest.setQuoteRequest(quoteRequestItem);
        quoteRequest.setConsumerQuoteReference(UUID.randomUUID().toString());
        quoteRequest.setSettlementAccountGroup("House Account");
        QuoteResponse quoteResponse = client.postQuote(quoteRequest);
        Assertions.assertNotNull(quoteResponse.getQuoteId());
        Assertions.assertNotNull(quoteResponse.getSubmittedDateTime());
        Assertions.assertNotNull(quoteResponse.getQuoteStatus());
        Assertions.assertNotNull(quoteResponse.getExpirationDateTime());
        Assertions.assertEquals(quoteRequest.getConsumerQuoteReference(), quoteResponse.getConsumerQuoteReference());
        Assertions.assertEquals(quoteRequest.getQuoteRequest().getBuyCurrency(), quoteResponse.getBuyCurrency());
        Assertions.assertEquals(quoteRequest.getQuoteRequest().getSellCurrency(), quoteResponse.getSellCurrency());
        Assertions.assertEquals(quoteRequest.getQuoteRequest().getSellAmount(), quoteResponse.getSellAmount());
        Assertions.assertEquals("EURUSD", quoteResponse.getCurrencyPair());
        Rate spotRate = quoteResponse.getSpotRate();
        if (spotRate != null) {
            Assertions.assertNotNull(spotRate.getBidRate());
            Assertions.assertNotNull(spotRate.getAskRate());
            Assertions.assertNotNull(spotRate.getMidRate());
            Assertions.assertNotNull(spotRate.getEffectiveDateTime());
        }
        SwapPoints swapPoints = quoteResponse.getSwapPoints();
        if (swapPoints != null) {
            Assertions.assertNotNull(swapPoints.getBidPoints());
            Assertions.assertNotNull(swapPoints.getAskPoints());
        }
        Rate allInRate = quoteResponse.getAllInRate();
        if (allInRate != null) {
            Assertions.assertNotNull(allInRate.getBidRate());
            Assertions.assertNotNull(allInRate.getAskRate());
            Assertions.assertNotNull(allInRate.getMidRate());
            Assertions.assertNotNull(allInRate.getEffectiveDateTime());
        }
        Assertions.assertNotNull(quoteResponse.getContraAmount());
        Assertions.assertNotNull(quoteResponse.getRate());
        Assertions.assertNotNull(quoteResponse.getSettlementDate());
        Assertions.assertNotNull(quoteResponse.getQuoteSignature());
    }

    @Test
    public void testPostQuoteWithBrokenDate() throws InterruptedException, IOException {
        QuoteRequest quoteRequest = new QuoteRequest();
        QuoteRequestItem quoteRequestItem = new QuoteRequestItem();
        quoteRequestItem.setBuyCurrency("EUR");
        quoteRequestItem.setSellCurrency("USD");
        quoteRequestItem.setSellAmount(getRandomAmount());
        quoteRequestItem.setSettlement("2025-12-25P"); // Bank holiday
        quoteRequest.setQuoteRequest(quoteRequestItem);
        quoteRequest.setConsumerQuoteReference(UUID.randomUUID().toString());
        quoteRequest.setSettlementAccountGroup("House Account");
        QuoteResponse quoteResponse = client.postQuote(quoteRequest);
        Assertions.assertEquals("2025-12-24", quoteResponse.getSettlementDate());
    }

    @Test
    public void testPostOrder() throws InterruptedException, IOException {
        BigDecimal buyAmount = getRandomAmount();
        // 1. Get quote
        QuoteRequest quoteRequest = new QuoteRequest();
        QuoteRequestItem quoteRequestItem = new QuoteRequestItem();
        quoteRequestItem.setBuyCurrency("EUR");
        quoteRequestItem.setBuyAmount(buyAmount);
        quoteRequestItem.setSellCurrency("USD");
        quoteRequestItem.setSettlement("ASAP");
        quoteRequest.setQuoteRequest(quoteRequestItem);
        quoteRequest.setConsumerQuoteReference(UUID.randomUUID().toString());
        quoteRequest.setSettlementAccountGroup("House Account");
        QuoteResponse quoteResponse = client.postQuote(quoteRequest);
        System.out.printf("Buying (%s, %.2f) for (%s, %.2f) at rate (%f)\n",
                          quoteRequestItem.getBuyCurrency(),
                          quoteRequestItem.getBuyAmount().doubleValue(),
                          quoteResponse.getSellCurrency(),
                          quoteResponse.getContraAmount(),
                          quoteResponse.getRate());
        // 2. Post order using the acquired quote
        OrderRequest orderRequest = new OrderRequest();
        OrderRequestItem orderRequestItem = new OrderRequestItem();
        orderRequestItem.setBuyCurrency("EUR");
        orderRequestItem.setBuyAmount(buyAmount);
        orderRequestItem.setSellCurrency("USD");
        orderRequestItem.setSettlement("ASAP");
        orderRequest.setOrderRequest(orderRequestItem);
        orderRequest.setSettlementAccountGroup("House Account");
        orderRequest.setQuoteSignature(quoteResponse.getQuoteSignature());
        OrderResponse orderResponse = client.postOrder(orderRequest);
        Assertions.assertEquals("FILLED", orderResponse.getOrderStatus(), "Order was not filled.");
        Assertions.assertEquals("EURUSD", orderResponse.getCurrencyPair(), "Unexpected currency pair.");
        Assertions.assertEquals(quoteResponse.getQuoteId(), orderResponse.getQuoteId(), "Quote ID changed.");
    }

    private BigDecimal getRandomAmount() {
        float randomValue = (new Random().nextFloat() % 10000) + 1000;
        return new BigDecimal(Float.toString(randomValue)).setScale(2, RoundingMode.HALF_UP);
    }
}
