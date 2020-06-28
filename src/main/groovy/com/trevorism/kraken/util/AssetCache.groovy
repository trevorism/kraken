package com.trevorism.kraken.util

import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.impl.DefaultPublicKrakenClient

class AssetCache {

    static AssetCache INSTANCE = new AssetCache()

    private def assets = [];
    private PublicKrakenClient client = new DefaultPublicKrakenClient()

    private AssetCache() {
    }

    def get() {
        if (!assets) {
            assets = client.getAssets()
        }
        return assets
    }

}
