package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Outlay;

public interface OutlayRepository {
    Outlay findByAcId(final String acId);
}
