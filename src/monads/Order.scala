package monads


// needed for Futures to work
import scala.concurrent.Future



// load the order for a given customer , get the item in question, make the purchase of the item and log the result


object main extends App {
import scala.concurrent.ExecutionContext.Implicits.global
  val result =
    for {
      loadedOrder    <- OrderService.loadOrder("customerUsername")
      loadedItem     <- ItemService.loadItem(loadedOrder)
      purchaseResult <- PurchasingService.purchaseItem(loadedItem)
      logResult      <- PurchasingService.logPurchase(purchaseResult)
    } yield logResult


  val result2 =
    OrderService.loadOrder("customerUsername")
      .flatMap(Service.loadItem)
      .flatMap(Service.purchaseItem)
      .flatMap(Service.logPurchase)


}



trait Order
trait Item
trait PurchaseResult
trait LogResult
object OrderService {
  def loadOrder(username: String): Future[Order] = ???
}
object ItemService {
  def loadItem(order: Order): Future[Item] = ???
}
object PurchasingService {
  def purchaseItem(item: Item): Future[PurchaseResult] = ???
  def logPurchase(purchaseResult: PurchaseResult): Future[LogResult] = ???
}


object Service {
  val loadItem: Order => Future[Item] = {
    order => ItemService.loadItem(order)
  }
  val purchaseItem: Item => Future[PurchaseResult] = {
    item => PurchasingService.purchaseItem(item)
  }

  val logPurchase: PurchaseResult => Future[LogResult] = {
    purchaseResult => PurchasingService.logPurchase(purchaseResult)
  }
}