# ABN AMRO FX Trade API Client Library
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fedinarorg%2Fabnamrofxtradeclient.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fedinarorg%2Fabnamrofxtradeclient?ref=badge_shield)

An open-source Java client library for the ABN AMRO FX Trade API. 

## Functionality
* [Basic authentication](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#section/Authentication)
* [`getAllowedCurrencyPairs`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getAllowedCurrencyPairs)
* [`getSettlementAccountGroups`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getSettlementAccountGroups)
* [`getFxRates`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxRates)
* [`getFxRate`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxRates)
* [`performConversionCalculations`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/performConversionCalculations)
* [`createFxQuote`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/createFxQuote)
* [`getFxQuotes`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxQuotes)
* [`getFxQuote`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxQuote)
* [`createFxOrder`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/createFxOrder)
* [`getFxOrders`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxOrders)
* [`getFxOrder`](https://developer.abnamro.com/api-products/fx-trade/reference-documentation#operation/getFxOrder)

## Run-time dependencies
See [`build.gradle.kts`](https://github.com/edinarorg/abnamrofxtradeclient/blob/main/lib/build.gradle.kts)

|Group|Name|Version|
|:---|:---|:---|
|`org.glassfish.jersey.core`|`jersey-client`|`3.0.4`|
|`com.fasterxml.jackson.core`|`jackson-databind`|`2.13.2.2`|


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fedinarorg%2Fabnamrofxtradeclient.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fedinarorg%2Fabnamrofxtradeclient?ref=badge_large)