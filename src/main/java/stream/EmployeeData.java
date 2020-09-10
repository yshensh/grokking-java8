package stream;

import java.util.ArrayList;
import java.util.List;
/**
 * 提供用于测试的数据
 * 
 * @author shkstart 邮箱：shkstart@126.com
 *
 */
public class EmployeeData {
	
	public static List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<>();

		list.add(new Employee(1001, "P1", 34, 6000.38));
		list.add(new Employee(1002, "P2", 12, 9876.12));
		list.add(new Employee(1003, "P3", 33, 3000.82));
		list.add(new Employee(1004, "P4", 26, 7657.37));
		list.add(new Employee(1005, "P5", 65, 5555.32));
		return list;
	}
}
