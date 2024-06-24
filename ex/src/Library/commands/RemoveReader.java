package Library.commands;

import Library.LibraryManagmentSystem;
import Library.interfaces.LabraryCommands;
import Library.persons.Reader;

import java.util.List;

public class RemoveReader implements LabraryCommands {
    @Override
    public String getName() {
        return "/remove_reader";
    }

    @Override
    public String getDescription() {
        return "Удалить читателя.";
    }

    @Override
    public void execute(LibraryManagmentSystem library) {
        System.out.print("Введите читательский билет: ");
        // Получаем список читателей, которые соответствуют введенному читательскому билету
        List<Reader> foundReaders = library.searchReaders(scanner.nextLine());

        // Если читатели не найдены, выводим сообщение и завершаем выполнение команды
        if (!foundReaders.get(0).getBorrowedItems().isEmpty()) {
            System.out.print("Читатель не вернул все книги. Хотите " +
                    "продолжить ?\n" +
                    "[Да/Нет]: ");
            String responseUser = scanner.nextLine().toLowerCase();
            // Цикл для получения корректного ответа от пользователя
            while (true){
                if("да".equalsIgnoreCase(responseUser)){
                    library.removeReader(foundReaders.get(0), true);
                    break;
                }else if("нет".equalsIgnoreCase(responseUser)){
                    break;
                }
                System.out.print("[Да/Нет]: ");
                responseUser = scanner.nextLine().toLowerCase();
            }

        }else {
            library.removeReader(foundReaders.get(0));
        }

    }
}
