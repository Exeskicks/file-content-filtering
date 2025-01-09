# Утилита фильтрации содержимого файлов.

## Описание проекта
Этот проект представляет собой утилиту фильтрации содержимого файлов. Программа принимает файлы формата txt и создаёт 3 файла целые числа, строки и вещественные числа.

---
## Функциональные возможности
 - Разное количесто принимаемых файлов
 - Файлы с результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt. Возможно добавление префикса.
 - Можно задать режим добавления в существующие файлы.
 - Собирать статистику по каждому типу данных.

---
## Требования
- Java: JDK 17
- Система сборки: Maven
- Логирование с помощью java.util
- Документация  maven-javadoc-plugin версия 3.11.2
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

### Выходные файлы (txt)
 - txt:
    - integers.txt — Все целые числа
    - floats.txt — Все вещественные числа
    - sample-strings.txt — строки.


Пример:
```txt
sample-integers.txt
45
1234567890123456789
100500
sample-floats.txt
1.528535047E-25
3.1415
-0.001
sample-strings.txt
Lorem ipsum dolor sit amet
Нормальная форма числа с плавающей запятой
Пример
Long
consectetur adipiscing
тестовое задание
```

---
## Как использовать
1. ### Сборка проекта:
    Выполните следующую команду для сборки JAR-файла:
    ```bash
    mvn clean package
    ```
2. ### Запуск приложения: 
   Запустите приложение с параметрами командной строки:
    ```bash
    java -jar target/filteringContentsFiles-1.0-SNAPSHOT.jar \
    -o /some/path \
    -p "prefix" \
    -a \
    -s or -f
    ```
3. ### Параметры командной строки:
   - `-o`: Путь для результатов.х.
   - `-p`: задает префикс имен выходных файлов.
   - `-a`: Режим добавления в существующие файлы.
   - `-s`: Краткая статистика.
   - `-f`: полная статистика.
   
---
## Пример запуска
```bash 
java -jar filteringContentsFiles-1.0-SNAPSHOT.jar -s -a -p result_ -o H:\JAVA in1.txt
```

