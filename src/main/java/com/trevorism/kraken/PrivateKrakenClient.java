package com.trevorism.kraken;

import com.trevorism.kraken.model.AssetBalance;

import java.util.Set;

public interface PrivateKrakenClient {

    Set<AssetBalance> getAccountBalance();


}
