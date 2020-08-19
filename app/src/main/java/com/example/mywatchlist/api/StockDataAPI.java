package com.example.mywatchlist.api;

import com.example.mywatchlist.entity.Data;
import com.example.mywatchlist.entity.Quote;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockSymbol;
import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StockDataAPI {
    @GET("ref-data/symbols")
    Observable<List<StockSymbol>> getStockSymbolNameList();

    @GET("stock/{symbol}/batch?types=quote,news,company,stats,earnings,estimates")
    Observable<Stock> getStock(@Path("symbol") String symbol);

    @GET("stock/{symbol}/chart/{range}")
    Observable<List<Data>>  getChart(@Path("symbol") String symbol, @Path("range") String range);

    @GET("stock/{symbol}/quote")
    Observable<Quote> getQuotes(@Path("symbol") String symbol);
}
