devtools::load_all ('target')

api <- DefaultApi$new ()
users <- api$read_users ()

counts <- list ()
for (id in users$content$id) {
    user <- api$read_user (id)
    dept <- user$content$department
    if (is.null (dept)) {
        dept <- 'none'
    }
    if (!(dept %in% names (counts))) {
        counts [[dept]] <- 0
    }
    counts [[dept]] <- counts [[dept]] + 1
}

barplot (unlist (counts), main = 'Department users count')
