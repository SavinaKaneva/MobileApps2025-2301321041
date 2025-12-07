package com.example.readingjournal;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Book> __insertAdapterOfBook;

  private final EntityDeleteOrUpdateAdapter<Book> __deleteAdapterOfBook;

  private final EntityDeleteOrUpdateAdapter<Book> __updateAdapterOfBook;

  public BookDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfBook = new EntityInsertAdapter<Book>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `book_table` (`id`,`title`,`author`,`pages`,`rating`,`review`,`color`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Book entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getAuthor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAuthor());
        }
        statement.bindLong(4, entity.getPages());
        statement.bindLong(5, entity.getRating());
        if (entity.getReview() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getReview());
        }
        statement.bindLong(7, entity.getColor());
      }
    };
    this.__deleteAdapterOfBook = new EntityDeleteOrUpdateAdapter<Book>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `book_table` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Book entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfBook = new EntityDeleteOrUpdateAdapter<Book>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `book_table` SET `id` = ?,`title` = ?,`author` = ?,`pages` = ?,`rating` = ?,`review` = ?,`color` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Book entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getAuthor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAuthor());
        }
        statement.bindLong(4, entity.getPages());
        statement.bindLong(5, entity.getRating());
        if (entity.getReview() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getReview());
        }
        statement.bindLong(7, entity.getColor());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object addBook(final Book book, final Continuation<? super Unit> $completion) {
    if (book == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfBook.insert(_connection, book);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object deleteBook(final Book book, final Continuation<? super Unit> $completion) {
    if (book == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfBook.handle(_connection, book);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateBook(final Book book, final Continuation<? super Unit> $completion) {
    if (book == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfBook.handle(_connection, book);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<List<Book>> readAllData() {
    final String _sql = "SELECT * FROM book_table ORDER BY id ASC";
    return __db.getInvalidationTracker().createLiveData(new String[] {"book_table"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfAuthor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "author");
        final int _columnIndexOfPages = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "pages");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfReview = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "review");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final List<Book> _result = new ArrayList<Book>();
        while (_stmt.step()) {
          final Book _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpAuthor;
          if (_stmt.isNull(_columnIndexOfAuthor)) {
            _tmpAuthor = null;
          } else {
            _tmpAuthor = _stmt.getText(_columnIndexOfAuthor);
          }
          final int _tmpPages;
          _tmpPages = (int) (_stmt.getLong(_columnIndexOfPages));
          final int _tmpRating;
          _tmpRating = (int) (_stmt.getLong(_columnIndexOfRating));
          final String _tmpReview;
          if (_stmt.isNull(_columnIndexOfReview)) {
            _tmpReview = null;
          } else {
            _tmpReview = _stmt.getText(_columnIndexOfReview);
          }
          final int _tmpColor;
          _tmpColor = (int) (_stmt.getLong(_columnIndexOfColor));
          _item = new Book(_tmpId,_tmpTitle,_tmpAuthor,_tmpPages,_tmpRating,_tmpReview,_tmpColor);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
