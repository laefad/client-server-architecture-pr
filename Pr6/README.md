Graphiql: http://localhost:8000/graphiql

Example queries and mutations 

    query getTags {
        getAllTags {
            name
            id
        }
        
        getTag(tagId: 1) {
            name
        }
    }

    mutation as {
        deleteTag(tagId: 1) {
            name
        }
    }

    mutation addTag {
        createTag(input: {
            name: "tag2"
        }) {
            name
        }
    }

    query books {
        getAllBooks {
            id
            name
            authors {
                id
                name
            }
            tags {
                name
            }
        }
    }

    mutation addBook {
        createBook(input: {
            username: "admin"
            name: "book3"
            description: "desk3"
            tagIds: [2]
        }) {
            id 
            name
        }
    }

    query getAuthors {
        getAllAuthors {
            name
            id
            books {
                name
            }
        }
    }

    mutation addAuthor {
        createAuthor(input: {
            name: "Чарльз"
            surname: "Диккенс"
            bookIds: [1, 2]
        }) {
            id
        }
    }

    query getUsers {
        getAllUserBookDetails {
            username
            books {
                id
                name
            }
        }
        getUserBookDetails(username: "admin") {
            username
        }
    }

    mutation addBookto {
        addBookToUser(input: {
            username: "bob"
            bookId: 1
        }) {
            username
        }
    }

    mutation removeBookfrom {
        removeBookFromUser(input: {
            username: "admin"
            bookId: 1
        }) {
            username
        }
    }
