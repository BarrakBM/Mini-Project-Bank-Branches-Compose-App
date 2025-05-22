package com.example.bankbranches.data


enum class BranchType(val value: String) {
    MAIN("Main"),
    DOWNTOWN("Downtown"),
    ATM("ATM"),
    RAWDA("Rawdha"),
    AVENUES("Avenues"),
    ALHAMRA("Al-Hamra Tower")
}

data class Branch(
    var id: Int,
    var name: String,
    var type: BranchType,
    var address: String,
    var phone: String,
    var hours: String,
    var location: String,
    var imageUri: Int? = null
)