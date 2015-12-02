//取值、新增修改刪除等資料存取介面
package ncu.edu.writing.dao;

import java.util.List;

public interface Dao<T> {
	public T get(int id);

	public List<T> get(List<Integer> ids);

	public void saveOrUpdate(T entity);

	public void delete(int id);

	public void delete(List<Integer> ids);

}
