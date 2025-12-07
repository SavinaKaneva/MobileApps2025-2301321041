package com.example.readingjournal;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0002J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J0\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0012\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/readingjournal/AddActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "colorCards", "", "Lcom/google/android/material/card/MaterialCardView;", "mBookViewModel", "Lcom/example/readingjournal/BookViewModel;", "selectedColor", "", "highlightCard", "", "activeCard", "inputCheck", "", "title", "", "author", "pages", "rating", "insertDataToDatabase", "etTitle", "Landroid/widget/EditText;", "etAuthor", "etPages", "etRating", "etReview", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class AddActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.readingjournal.BookViewModel mBookViewModel;
    private int selectedColor;
    private java.util.List<? extends com.google.android.material.card.MaterialCardView> colorCards;
    
    public AddActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void highlightCard(com.google.android.material.card.MaterialCardView activeCard) {
    }
    
    private final void insertDataToDatabase(android.widget.EditText etTitle, android.widget.EditText etAuthor, android.widget.EditText etPages, android.widget.EditText etRating, android.widget.EditText etReview) {
    }
    
    private final boolean inputCheck(java.lang.String title, java.lang.String author, java.lang.String pages, java.lang.String rating) {
        return false;
    }
}