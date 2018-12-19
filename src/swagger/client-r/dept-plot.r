source("init.r")

api <- DefaultApi$new()

all.users.id <- api$read_users()$content$id

department.people.count <- list()

for (i in all.users.id) {
    u <- api$read_user(i)$content
    dept <- u$department
    if (!(dept %in% names(department.people.count))) {
        department.people.count[[ dept ]] <- 0
    }
    
    department.people.count[[ dept ]] <- department.people.count[[ dept ]] + 1
}

barplot(unlist(department.people.count), main="Employee count per department")
