Back для интернет магазина. REST-приложение, содержащий контроллеры для работы с фронт частью, сервисы в которых содержится бизнес логика, сущности для хранения в БД, репозитории для работы с сущностями, DTO объекты.

Контроллеры:
1. AdsController
2. AuthController
3. UserController
4. ImageController

Сервисы:
1. AdsServiceImpl
2. AuthServiceImpl
3. UserServiceImpl
4. ImageServiceImpl

Сущности:
1. Advert
2. Commentary
3. Client
4. Authority
5. Picture

Репозитории для работы с сущностями:
1. AdvertRepository
2. CommentaryRepository
3. ClientRepository
4. AuthorityRepository
5. PictureRepository

Объекты DTO:
1. для работы с объявлениями Ads, AdList, CreateAds, FullAd
2. для работы с комментариями Comment, CommentsList
3. для работы с пользователями User, Role, LoginReq, RegisterReq, NewPassword
4. для работы с изображениями Image

Класс для маппинга Mapper. Осуществляет преобразования сущностей Бд в DTO, а также обратное преобразование

Конфигурации безопасности WebSecurityConfig

Класс для определения доступа действия UserAccessControl содержит метод accessControl, который возвращает истину/ложь, которое используется для разрешения/запрета действия



