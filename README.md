#Утилита фильтрации содержимого файлов.

Этот проект представляет собой утилиту фильтрации содержимого файлов. Программа принимает файлы формата txt и создаёт 3 файла целые числа, строки и вещественные числа.

## Функциональные возможности
 - Разное количесто принимаемых файлов
 - Файлы с результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt. Возможно добавление префикса.
 - Можно задать режим добавления в существующие файлы.
 - Собирать статистику по каждому типу данных.

 ---
## Требования
- Java: JDK 17
- Система сборки: Maven

---

## Формат данных
### фнесколько файлов (txt)
- txt- с данными , где:
    - Содержащих в перемешку целые числа, строки и вещественные числа.
    - В качестве разделителя используется перевод строки.
    - Остальные колонки — дополнительные свойства.

Пример:
```txt
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```