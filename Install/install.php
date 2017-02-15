<?php
/**
 * UIoT Service Layer
 * @version alpha
 *                          88
 *                          ""              ,d
 *                                          88
 *              88       88 88  ,adPPYba, MM88MMM
 *              88       88 88 a8"     "8a  88
 *              88       88 88 8b       d8  88
 *              "8a,   ,a88 88 "8a,   ,a8"  88,
 *               `"YbbdP'Y8 88  `"YbbdP"'   "Y888
 *
 * @author Universal Internet of Things
 * @license MIT <https://opensource.org/licenses/MIT>
 * @copyright University of Brasília
 *
 * This script will install all the required couchbase buckets that RAISe needs to work properly.
 * IMPORTANT: this requires the cURL extension to work.
 */
//include "metadados.php";
class Install
{
    private $username = "iury_adm"; //your couchbase username
    private $password = "123456"; // your couchbase password
    private function getCredentials()
    {
        return $this->username.":".$this->password;
    }
    private $postfields = array(
        'authType' => 'sasl',
        'bucketType' => 'membase',
        'flushEnabled' => 0,
        'name' => '',
        'ramQuotaMB' => '',
        'replicaIndex' => 0,
        'replicaNumber' => 1,
        'threadsNumber' => 3
    );
    private $buckets = array(
        'metadata' => 0,
        'client' => 0,
        'service' => 0,
        'token' => 0,
        'data' => 0,
        'network'  => 0,
        'user'  => 0,
        'log'  => 0
    );
    private function setFields($name, $quota)
    {
        $this->postfields['name'] = $name;
        $this->postfields['ramQuotaMB'] = $quota;
    }
    private function getFields()
    {
        return $this->postfields;
    }
    private function getBuckets()
    {
        return $this->buckets;
    }
    private function setBuckets($memory)
    {
        $buckets = $this->getBuckets();
        $buckets['metadata'] = floor((($memory / 100) * 2));
        $buckets['client'] = floor((($memory / 100) * 14));
        $buckets['service'] = floor((($memory / 100) * 14));
        $buckets['token'] = floor((($memory / 100) * 14));
        $buckets['data'] = floor((($memory / 100) * 14));
        $buckets['network'] = floor((($memory / 100) * 14));
        $buckets['user'] = floor((($memory / 100) * 14));
        $buckets['log'] = floor((($memory / 100) * 14));
        $this->buckets = $buckets;
    }
    private function getBucketsInfo()
    {
      $ch = curl_init();
      curl_setopt($ch, CURLOPT_USERPWD, $this->getCredentials());
      curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
      curl_setopt($ch, CURLOPT_URL, "http://localhost:8091//pools/default/buckets");
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      $server_output = curl_exec($ch);
      curl_setopt($ch, CURLOPT_VERBOSE, true);
      curl_close($ch);
      return JSON_decode($server_output);
    }
    public function getServerInfo()
    {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_USERPWD, $this->getCredentials());
        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_URL, "http://localhost:8091/pools/default");
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $server_output = curl_exec($ch);
        curl_setopt($ch, CURLOPT_VERBOSE, true);
        curl_close($ch);
        $this->setBuckets(JSON_decode($server_output)->memoryQuota);
        $this->createBuckets();
    }
    private function createBuckets()
    {
        $buckets = $this->getBuckets();
        foreach ($buckets as $key => $bucket)
        {
            $this->setFields($key, $bucket);
            $this->createBucket($this->getFields());
        }
        $this->fillBuckets();
    }
    private function fillBuckets()
    {
      $readyToFill = false;
      while($readyToFill !== true)
      {
        $status = array();
        $bucketsInfo = $this->getBucketsInfo();
        foreach($bucketsInfo as $key=>$info)
        {
          $status[] = $info->nodes[0]->status;
        }
        if (count(array_unique($status)) === 1 && end($status) === 'healthy')
        {
          $readyToFill = true;
        }
      }
      //From this point on, buckets are ready to be filled, so we are including the second part of the script
      include "bucktKey.php";
    }
    private function createBucket($postfields)
    {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_USERPWD, $this->getCredentials());
        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_URL, "http://localhost:8091/pools/default/buckets");
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($postfields));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $server_output = curl_exec($ch);
        curl_setopt($ch, CURLOPT_VERBOSE, true);
        curl_close($ch);
    }
}
$install = new Install;
$install->getServerInfo();
