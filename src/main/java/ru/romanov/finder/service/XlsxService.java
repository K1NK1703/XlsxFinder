package ru.romanov.finder.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.PriorityQueue;

@Service
public class XlsxService {

    public Integer findMax(String filePath, Integer n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int number = (int) cell.getNumericCellValue();
                        minHeap.offer(number);
                        if (minHeap.size() > n) {
                            minHeap.poll();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage());
        }

        if (minHeap.size() < n) {
            throw new IllegalArgumentException("Файл содержит менее " + n + " чисел");
        }

        return minHeap.poll();
    }
}
