// Получите текущий URL
const currentUrl = window.location.href;

// Проверьте URL и установите заголовок страницы
if (currentUrl.includes('/edit')) {
    document.title = 'Редактирование профиля';
} else if (currentUrl.includes('/users/add-user')) {
    document.title = 'Добавление нового пользователя';
}