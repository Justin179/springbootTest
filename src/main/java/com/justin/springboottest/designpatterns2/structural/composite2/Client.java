package com.justin.springboottest.designpatterns2.structural.composite2;

import java.util.ArrayList;
import java.util.List;
/*
當然！Composite Pattern 是一種設計模式，它可以將對象組織成樹形結構，並使客戶端以相同的方式處理個別對象和對象組合。它由以下幾個部分組成：

Component：是所有對象的基本接口，用於管理子組件的公共方法。它可以是接口或抽象類。
Leaf：實現基本行為的組件。它不包含對其他對象的引用。
Composite：包含子組件的組件。它實現了基本組件方法並定義了與子組件相關的操作。
Client：通過使用基本組件對象訪問組合元素。

Department
FinancialDepartment SalesDepartment
HeadDepartment
 */
public class Client {
    public static void main(String[] args) {
        Department salesDepartment = new SalesDepartment();
        Department financialDepartment = new FinancialDepartment();

        HeadDepartment headDepartment = new HeadDepartment();
        headDepartment.addDepartment(salesDepartment);
        headDepartment.addDepartment(financialDepartment);

        headDepartment.printDepartmentName();
    }
}

interface Department {
    void printDepartmentName();
}

class FinancialDepartment implements Department {
    public void printDepartmentName() {
        System.out.println("Financial Department");
    }
}

class SalesDepartment implements Department {
    public void printDepartmentName() {
        System.out.println("Sales Department");
    }
}

class HeadDepartment implements Department {
    private final List<Department> childDepartments = new ArrayList<>();

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }

    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }
}

