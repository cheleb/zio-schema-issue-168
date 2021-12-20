package example

final case class Block(
    baseFeePerGas: String,
    hash: String,
    transactions: List[String]
)
