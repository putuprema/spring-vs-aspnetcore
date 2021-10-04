package main

import (
	"EaterGo/controller"
	"EaterGo/dto"
	"EaterGo/middleware"
	"EaterGo/model"
	"EaterGo/repository"
	"EaterGo/service"
	"EaterGo/utils"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
	"gorm.io/gorm/schema"
	"net/http"
	"os"
)

var (
	db = bootstrapDb()
	// Repositories
	productOrderRepository = repository.NewProductOrderRepository(db)
	// Services
	productOrderService = service.NewProductOrderService(productOrderRepository)
	tokenService = service.NewTokenService(os.Getenv("SECRET_KEY"))
	// Middlewares
	authMiddleware = middleware.NewAuthMiddleware(tokenService)
	// Controller
	productOrderController = controller.NewProductOrderController(productOrderService)
)

func main() {
	gin.SetMode(gin.ReleaseMode)
	server := gin.New()
	server.Use(gin.Recovery())

	setupErrorHandlers(server)
	setupRoutes(server)

	if err := server.Run(fmt.Sprintf(":%s", utils.GetEnv("SERVER_PORT", "8080"))); err != nil {
		panic(err)
	}
}

func setupRoutes(server *gin.Engine) {
	v1Orders := server.Group("/v1/orders", authMiddleware.Authorize())
	{
		v1Orders.GET("/:id", productOrderController.GetOrderById())
	}
}

func bootstrapDb() *gorm.DB {
	loadEnv()

	dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s?charset=utf8&parseTime=True&loc=Local",
		os.Getenv("DB_USERNAME"),
		os.Getenv("DB_PASSWORD"),
		os.Getenv("DB_HOST"),
		os.Getenv("DB_NAME"),
	)

	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{
		Logger: logger.Default.LogMode(logger.Error),
		NamingStrategy: schema.NamingStrategy{SingularTable: true},
	})
	if err != nil {
		panic(err)
	}

	sqlDb, err := db.DB()
	if err != nil {
		panic(err)
	}
	sqlDb.SetMaxIdleConns(10)
	sqlDb.SetMaxOpenConns(50)

	if err := db.AutoMigrate(
		&model.Customer{},
		&model.ProductOrder{},
		&model.OrderItem{});
	err != nil {
		panic(err)
	}

	return db
}

func setupErrorHandlers(server *gin.Engine) {
	server.HandleMethodNotAllowed = true

	server.NoRoute(func(ctx *gin.Context) {
		ctx.JSON(http.StatusNotFound,
			dto.NewBaseResponse("Not Found"))
	})

	server.NoMethod(func(ctx *gin.Context) {
		ctx.JSON(http.StatusMethodNotAllowed,
			dto.NewBaseResponse("Method Not Allowed"))
	})
}

func loadEnv() {
	if err := godotenv.Load(); err != nil {
		panic(err)
	}
}