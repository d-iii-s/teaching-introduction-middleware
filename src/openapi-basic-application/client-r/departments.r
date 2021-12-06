devtools::load_all ('target')

api <- DefaultApi$new ()
api$initialize ()
users <- api$ReadUsers ()

counts <- list ()
for (user in users) {
    id <- user$id
    user <- api$ReadUser (id)
    dept <- user$department
    if (is.null (dept)) {
        dept <- 'none'
    }
    if (!(dept %in% names (counts))) {
        counts [[dept]] <- 0
    }
    counts [[dept]] <- counts [[dept]] + 1
}

barplot (unlist (counts), main = 'Department users count')
