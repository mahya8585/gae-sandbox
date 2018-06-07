/**
 * Triggered from a message on a Cloud Storage bucket.
 *
 * @param {!Object} event The Cloud Functions event.
 * @param {!Function} The callback function.
 */
exports.processFile = (event, callback) => {
  console.log('-----insert data create -------');

  //TODO ファイル読み込み

  //TODO sort

  //TODO 辞書データから3つずつ候補を選出

  //TODO 最初の一文字をkindにしてデータを作成

  //TODO ファイル化

  //TODO endバケットに配置

  callback();
};
