// Получите текущий URL
const currentUrl = window.location.href;

// Проверьте URL и установите заголовок страницы
if (currentUrl.includes('/failed-tasks/task-info')) {
    document.title = 'Информация о проваленной задаче';
} else if (currentUrl.includes('/completed-tasks/task-info')) {
    document.title = 'Информация о выполненной задаче';
}