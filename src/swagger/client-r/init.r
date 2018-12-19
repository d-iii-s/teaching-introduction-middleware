

if (!exists("INVENTORY.INITIALIZED")) {
    if (!require(devtools)) {
        install.packages("devtools")
    }
    
    library(devtools)
    install("inventory")

    
    source("inventory/R/Element.r")
    source("inventory/R/Response.r")
    source("inventory/R/UserBase.r")
    source("inventory/R/User.r")
    source("inventory/R/ApiClient.r")
    source("inventory/R/DefaultApi.r")
        
    INVENTORY.INITIALIZED <<- TRUE
}


