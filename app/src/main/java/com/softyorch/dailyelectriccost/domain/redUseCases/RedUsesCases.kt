package com.softyorch.dailyelectriccost.domain.redUseCases

data class RedUsesCases(
    val getDataBalance: GetDataBalance,
    val getDataGenerationTruncate: GetDataGenerationTruncate,
    val getDataMarketsTruncate: GetDataMarketsTruncate
)
