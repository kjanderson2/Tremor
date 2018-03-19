package android.kaitlynanderson.com.tremor

/**
 * Created by kaitlynanderson on 3/18/18.
 */
public interface Command<out T> {
    fun execute(): T
}