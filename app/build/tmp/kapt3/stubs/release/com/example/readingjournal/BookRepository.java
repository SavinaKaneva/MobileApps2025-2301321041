package com.example.readingjournal;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lcom/example/readingjournal/BookRepository;", "", "bookDao", "Lcom/example/readingjournal/BookDao;", "(Lcom/example/readingjournal/BookDao;)V", "readAllData", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/readingjournal/Book;", "getReadAllData", "()Landroidx/lifecycle/LiveData;", "addBook", "", "book", "(Lcom/example/readingjournal/Book;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBook", "updateBook", "app_release"})
public final class BookRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.readingjournal.BookDao bookDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.readingjournal.Book>> readAllData = null;
    
    public BookRepository(@org.jetbrains.annotations.NotNull()
    com.example.readingjournal.BookDao bookDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.readingjournal.Book>> getReadAllData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addBook(@org.jetbrains.annotations.NotNull()
    com.example.readingjournal.Book book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBook(@org.jetbrains.annotations.NotNull()
    com.example.readingjournal.Book book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBook(@org.jetbrains.annotations.NotNull()
    com.example.readingjournal.Book book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}